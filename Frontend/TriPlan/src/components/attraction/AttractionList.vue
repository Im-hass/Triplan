<template>
  <div class="list-wrap">
    <div v-if="attractions && attractions.length != 0">
      <b-list-group-item
        v-for="(attraction, index) in attractions"
        :key="index"
        @click="clickedAttraction(attraction.contentId)"
      >
        <div>
          <div class="title-wrap">
            <div class="title">{{ attraction.title }}</div>
            <div>
              <a @click="openModal(attraction.contentId)" target="_blank" class="link">상세보기</a>
            </div>
          </div>
          <span class="type">{{ attraction.contentTypeId | formatContentType }}</span>
          <br />
          <span>{{ attraction.addr1 }}</span>
        </div>
        <div id="img-and-btn">
          <div id="add-btn">
            <b-button variant="outline-secondary" @click="addPlan(attraction)" v-show="isWrite"
              >추가</b-button
            >
          </div>
          <img :src="attraction.firstImage" alt="" />
        </div>
      </b-list-group-item>
    </div>
    <div v-else class="searchEmpty"><span>검색 결과가 없습니다.</span></div>
    <!-- <attraction-detail-modal></attraction-detail-modal> -->
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from 'vuex';
// import AttractionDetailModal from "./AttractionDetailModal.vue";

const attractionStore = 'attractionStore';

export default {
  name: 'AttractionList',
  data() {
    return {
      isWrite: false,
      planAttractionInfo: {
        attractions: [],
        contentIds: [],
      },
    };
  },
  components: {
    // AttractionDetailModal,
  },
  computed: {
    ...mapState(attractionStore, ['attractions']),
  },
  filters: {
    formatContentType(contentTypeId) {
      const contentTypes = [
        { text: '관광지', value: 12 },
        { text: '문화시설', value: 14 },
        { text: '축제/공연/행사', value: 15 },
        { text: '여행코스', value: 25 },
        { text: '레포츠', value: 28 },
        { text: '숙박', value: 32 },
        { text: '쇼핑', value: 38 },
        { text: '음식점', value: 39 },
      ];
      return contentTypes.filter((v) => v.value == contentTypeId)[0].text;
    },
  },
  created() {
    this.CLEAR_ATTRACTION_LIST();
    console.log(this.$route.path);
    if (this.$route.path === '/plan/write') this.isWrite = true;
    else this.isWrite = false;
    this.detailAttraction(125266);
  },
  methods: {
    ...mapMutations(attractionStore, ['CLEAR_ATTRACTION_LIST']),
    ...mapActions(attractionStore, ['detailAttraction']),
    openModal(contentId) {
      this.detailAttraction(contentId);
      this.$bvModal.show('modal-detail');
    },
    clickedAttraction(contentId) {
      this.$emit('clicked-attraction', contentId);
      // this.detailAttraction(contentId);
    },
    addPlan(attraction) {
      this.planAttractionInfo.attractions.push(attraction);
      this.planAttractionInfo.contentIds.push(attraction.contentId);
      this.$emit('add-plan-attraction', this.planAttractionInfo);
    },
  },
};
</script>

<style scoped>
a {
  color: #8d9193;
}

.title {
  font-size: 1rem;
  color: #383838;
}

.type {
  font-size: 0.8rem;
  color: #51abf3;
}

.col-xl-4 {
  padding: 0px;
}

.list-wrap {
  width: 100%;
  overflow: auto;
}

.list-group-item {
  border-bottom: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
  display: flex;
  justify-content: space-between;
}

.list-group-item:hover {
  background: #fafafa;
}

.searchEmpty {
  color: #8d9193;
  padding: 30px 0px;
  width: 100%;
  text-align: center;
  border-top: 0.3px rgba(0, 0, 0, 0.125);
  border-style: solid hidden;
}

#add-btn {
  padding-bottom: 8px;
}

#img-and-btn {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

img {
  width: 120px;
  border-radius: 2px;
}

@media (max-width: 1199px) {
  .list-wrap {
    margin-top: 20px;
  }
}
</style>
