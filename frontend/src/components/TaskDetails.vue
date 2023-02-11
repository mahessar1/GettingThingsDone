<template> 
  <ion-modal ref="modal" :trigger="props.taskId">
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="end">
          <ion-button @click="cancel()">Close</ion-button>
        </ion-buttons>
        <ion-title>{{ taskDetails.title }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-list>
      <ion-item>
        <ion-label>ID: </ion-label>
        <ion-label>{{ taskDetails.id }}</ion-label>
      </ion-item>
      <ion-item>
        <ion-label>Created: </ion-label>
        <ion-label>{{
          taskDetails.created.substring(0, taskDetails.created.indexOf("T")) +
          " " +
          taskDetails.created.substring(11, 19) +
          " Uhr"
        }}</ion-label>
      </ion-item>
      <ion-item>
        <ion-label>Description: </ion-label>
        <ion-label>{{ taskDetails.description }}</ion-label>
      </ion-item>
      <ion-item>
        <ion-label>Due date: </ion-label>
        <ion-label>{{
          taskDetails.dueDate.substring(0, taskDetails.dueDate.indexOf("T")) +
          " " +
          taskDetails.dueDate.substring(11) +
          " Uhr"
        }}</ion-label>
      </ion-item>
      <ion-item>
        <ion-label>Status: </ion-label>
        <ion-label>{{ taskDetails.status }}</ion-label>
      </ion-item>
    </ion-list>
  </ion-modal>
  
</template>

<script setup lang="ts">
import {
  IonModal,
  IonButtons,
  IonButton,
  IonItem,
  IonLabel,
  IonListHeader,
  IonList,
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
} from "@ionic/vue";
import axios from "axios";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { OverlayEventDetail } from "@ionic/core/components";

const taskDetails = ref<any>([]);
const props = defineProps({
  taskId: Number,
  taskId2: Number
});
const modal = ref(null);

async function getTaskDetails() {
  const config = {
    headers: {
      "Content-Type": "application/json",
      "x-apikey": "5b2e750b0c346a20d90a5dda",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/tasks/" + props.taskId,
    config
  );
  taskDetails.value = response.data;
  console.log(taskDetails);
}

function cancel() {
  location.reload();
}

onMounted(() => {
  getTaskDetails();
});


</script>