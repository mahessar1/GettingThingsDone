<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Task Details</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Task Details</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-button></ion-button>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
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

const taskDetails = ref<any>([]);

async function getTaskDetails() {
  const config = {
    headers: {
      "Content-Type": "application/json",
      "x-apikey": "5b2e750b0c346a20d90a5dda",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/tasks/:id",
    config
  );
  taskDetails.value = response.data;
}
onMounted(() => {
  getTaskDetails();
});
</script>