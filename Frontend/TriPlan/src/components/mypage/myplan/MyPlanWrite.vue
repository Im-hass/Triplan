<template>
  <div id="wrap">
    <div class="side-wrap">
      <attraction-search-bar></attraction-search-bar>
      <attraction-list
        @clicked-attraction="openOverlay"
        @add-plan-attraction="addPlanAttraction"
      ></attraction-list>
    </div>
    <div class="side-wrap write-plan">
      <b-form @submit="onPlanSubmit" class="plan-form">
        <b-row class="date-picker-wrap">
          <b-col
            ><label class="date">여행 시작일</label>
            <b-form-datepicker
              v-model="form.startDate"
              placeholder="연도.월.일"
              :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
            ></b-form-datepicker
          ></b-col>
          <b-col
            ><label class="date">여행 종료일</label>
            <b-form-datepicker
              v-model="form.endDate"
              placeholder="연도.월.일"
              :date-format-options="{ year: 'numeric', month: 'numeric', day: 'numeric' }"
            ></b-form-datepicker
          ></b-col>
        </b-row>
        <b-row>
          <b-col cols="9"
            ><b-form-input
              v-model="form.title"
              placeholder="플랜 이름을 작성해주세요."
            ></b-form-input
          ></b-col>
          <b-col><b-button type="submit" class="submit-btn btn-primary">등록</b-button></b-col>
        </b-row>
      </b-form>
      <div v-if="planAttractions && planAttractions.length != 0" class="plan-list">
        <div class="courseMention">
          <span>드래그 앤 드랍으로 순서를 변경할 수 있어요.</span>
        </div>
        <b-card
          v-for="(attraction, index) in planAttractions"
          :key="index"
          :img-src="attraction.firstImage"
          img-alt="Card image"
          img-left
          class="mb-3"
          draggable="true"
          @drop.prevent="onDrop($event, index)"
          @dragenter.prevent
          @dragover.prevent
          @dragstart="startDrag($event, attraction)"
        >
          <b-card-text>
            {{ attraction.title }}
          </b-card-text>
          <b-button @click="delPlanAttraction(attraction.contentId)" variant="outline-danger"
            >삭제</b-button
          >
        </b-card>
      </div>
      <div v-else class="courseMention"><span>플랜 여행지를 추가해주세요.</span></div>
    </div>
    <kakao-map :attractions="attractions" :peekList="peekList"></kakao-map>
  </div>
</template>

<script>
import KakaoMap from '@/components/attraction/KakaoMap.vue';
import AttractionSearchBar from '@/components/attraction/AttractionSearchBar.vue';
import AttractionList from '@/components/attraction/AttractionList.vue';
import { mapState } from 'vuex';
import { registPlan } from '@/api/plan';

const attractionStore = 'attractionStore';
const memberStore = 'memberStore';

export default {
  name: 'MyPlanWrite',
  data() {
    return {
      form: {
        title: '',
        startDate: '',
        endDate: '',
      },
      members: {
        owner: '',
        member: [],
      },
      peekList: null,
      planAttractions: [],
    };
  },
  computed: {
    ...mapState(attractionStore, ['attractions']),
    ...mapState(memberStore, ['userInfo']),
  },
  components: {
    KakaoMap,
    AttractionSearchBar,
    AttractionList,
  },
  methods: {
    onPlanSubmit(event) {
      event.preventDefault();
      let thumbnailUrl = '';
      for (let i = 0; i < this.planAttractions.length; i++) {
        if (this.planAttractions[i].firstImage != '') {
          thumbnailUrl = this.planAttractions[i].firstImage;
          break;
        }
      }

      let courseList = [];

      for (let i = 0; i < this.planAttractions.length; i++) {
        console.log(this.planAttractions[i]);
        let course = {
          contentId: this.planAttractions[i].contentId,
          order: i + 1,
        };

        courseList.push(course);
      }

      this.members.owner = this.userInfo.memberId;

      let param = {
        title: this.form.title,
        startDate: this.form.startDate,
        endDate: this.form.endDate,
        thumbnailUrl: thumbnailUrl,
        members: this.members,
        courseList: courseList,
      };
      registPlan(
        param,
        ({ data }) => {
          console.log(data);
          alert('플랜이 등록되었습니다.');
        },
        (error) => {
          console.log(error);
        }
      );
    },
    openOverlay(contentid) {
      this.peekList = contentid;
    },
    addPlanAttraction(planAttractionsInfo) {
      this.planAttractions = planAttractionsInfo.attractions;
    },
    delPlanAttraction(contentId) {
      for (let i = 0; i < this.planAttractions.length; i++) {
        if (this.planAttractions[i].contentId === contentId) {
          this.planAttractions.splice(i, 1);
          break;
        }
      }
    },
    startDrag(event, item) {
      event.dataTransfer.setData('selectedItem', item.contentId);
    },
    onDrop(event, idx) {
      const selectedItem = Number(event.dataTransfer.getData('selectedItem'));

      let targetIdx;
      let targetItem;
      this.planAttractions.forEach((obj, index) => {
        if (obj.contentId === selectedItem) {
          targetIdx = index;
          targetItem = obj;
        }
      });

      this.planAttractions.splice(targetIdx, 1);
      this.planAttractions.splice(idx, 0, targetItem);
    },
  },
};
</script>

<style scoped>
#wrap {
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

.date {
  color: #51abf3;
  font-weight: 500;
}

.courseMention {
  color: #8d9193;
  padding: 30px 0px;
  width: 100%;
  /* height: 560px; */
  text-align: center;
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

.btn-outline-danger {
  right: 5%;
  position: absolute;
  bottom: 15%;
}

.submit-btn {
  cursor: pointer;
  height: calc(1.5em + 0.75rem + 2px);
  width: 100%;
}

.col,
.col-9 {
  padding: 0px;
}

.row {
  padding: 15px;
  gap: 10px;
}

.side-wrap {
  background-color: #fff;
  z-index: 21;
  width: 25%;
  position: absolute;
  padding: 1rem;
  height: calc(100vh - 60px);
  /* box-shadow: 15px 0px 20px #a0a0a09b; */
}

.list-wrap {
  height: calc(100vh - 450px);
}

.write-plan {
  right: 0%;
  overflow: auto;
}

.plan-list {
  height: calc(100vh - 270px);
  /* padding-top: 30px; */
}

.plan-form {
  padding-bottom: 15px;
}

img {
  width: 220px;
  height: 50%;
}

.form-control {
  margin-bottom: 20px;
}

#map {
  height: calc(100vh - 60px);
}
</style>
