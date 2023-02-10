<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Projecttasks</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="ion-padding">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Projects</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-card v-for="task in listTasks" v-bind:key="task">
        <ion-card-header>
          <ion-card-title>{{ task.title }} </ion-card-title>
        </ion-card-header>

        <ion-card-content>
          <ion-button fill="clear" :id="task.id">
            <task-details :taskId="task.id"></task-details>
            <ion-icon slot="end" :icon="eye"></ion-icon>
            View Task</ion-button
          >
        </ion-card-content>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCard,
  IonButton,
  IonFab,
  IonFabButton,
  IonIcon,
  alertController,
} from "@ionic/vue";
import { addCircle, eye, pencil, trash, checkmarkCircle } from "ionicons/icons";
import axios from "axios";
import { onMounted, onUpdated, ref } from "vue";
import { useRoute } from "vue-router";
import TaskDetails from "../components/TaskDetails.vue";

const listTasks = ref<any>([]);

async function getListTasks() {
  const route = useRoute();

  const id = route.params.id;
  const config = {
    headers: {
      "Content-Type": "application/json",
      "x-apikey": "5b2e750b0c346a20d90a5dda",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/lists/" + id + "/tasks",
    config
  );
  listTasks.value = response.data;
}
onMounted(() => {
  getListTasks();
});

</script>

<style scoped>
ion-list-header {
  text-align: center;
}
</style>