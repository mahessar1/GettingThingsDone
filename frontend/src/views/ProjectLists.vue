<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Projects</ion-title>
        <ion-buttons slot="end">
          <ion-button fill="solid" color="primary">
            Add new Project
            <ion-icon slot="end" :icon="addCircle"></ion-icon>
          </ion-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="ion-padding">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Projects</ion-title>
        </ion-toolbar>
      </ion-header>
    
      <ion-card v-for="project in projects" v-bind:key="project">
        <ion-card-header>
          <ion-card-title
            >{{ project }}
            <ion-button fill="solid" color="success" size="small" style="float: right">
              Finish Project
              <ion-icon slot="end" :icon="checkmarkCircle"></ion-icon>
            </ion-button>
          </ion-card-title>
        </ion-card-header>

        <ion-card-content> Project Description </ion-card-content>

        <ion-button fill="clear" router-link="/tabs/taskdetails"
          >View Tasks</ion-button
        >
        <ion-button fill="clear">Edit Project</ion-button>
        <ion-button fill="clear" color="danger" @click="presentAlert"
          >Delete Project</ion-button
        >
        <p>{{ handlerMessage }}</p>
        <p>{{ roleMessage }}</p>
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
  alertController
} from "@ionic/vue";
import { ref } from "vue";
import { addCircle, checkmarkCircle } from "ionicons/icons";

const handlerMessage = ref("");
const roleMessage = ref("");

const presentAlert = async () => {
  const alert = await alertController.create({
    header: "Are you sure?",
    buttons: [
      {
        text: "No",
        role: "cancel",
        handler: () => {
          handlerMessage.value = "Alert canceled";
        },
      },
      {
        text: "Yes",
        role: "confirm",
        handler: () => {
          handlerMessage.value = "Project deleted";
        },
      },
    ],
  });

  await alert.present();

  const { role } = await alert.onDidDismiss();
  roleMessage.value = `Dismissed with role: ${role}`;
};

const projects = ref<any>([]);
projects.value = [
  "Steuererklärung",
  "Hauskauf",
  "Haustier adoptieren",
  "Heimkino",
];
</script>

<style scoped>
ion-list-header {
  text-align: center;
}

</style>