<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Projects</ion-title>
        <ion-buttons slot="end">
          <ion-button
            fill="solid"
            color="primary"
            router-link="/tabs/newproject"
          >
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
            >{{ project.title }}
            <ion-button
              fill="solid"
              color="success"
              size="small"
              style="float: right"
            >
              Finish Project
              <ion-icon slot="end" :icon="checkmarkCircle"></ion-icon>
            </ion-button>
          </ion-card-title>
        </ion-card-header>

        <ion-card-content> {{ project.description }} </ion-card-content>

        <ion-button fill="clear" :router-link="'/tabs/projecttask/' + project.id" 
          >View Tasks
          <ion-icon slot="end" :icon="eye"></ion-icon>
        </ion-button>
        <ion-button fill="clear"
          >Edit Project
          <ion-icon slot="end" :icon="pencil"></ion-icon>
        </ion-button>
        <ion-button
          fill="clear"
          color="danger"
          @click="presentAlert(project.id)"
          >Delete Project
          <ion-icon slot="end" :icon="trash"></ion-icon>
        </ion-button>
        <p>{{ handlerMessage }}</p>
        <p>{{ roleMessage }}</p>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonButtons,
  
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

const handlerMessage = ref("");
const roleMessage = ref("");
const projects = ref<any>([]);

const presentAlert = async (id) => {
  const alert = await alertController.create({
    header: "Are you sure? All assigned task will be deleted",
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
          axios.delete("http://localhost:8080/api/lists/projectlists/" + id);
          location.reload();
        },
      },
    ],
  });

  await alert.present();
};


async function getProjects() {
  const config = {
    headers: {
      "Content-Type": "application/json",
      "x-apikey": "5b2e750b0c346a20d90a5dda",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/lists/projectlists",
    config
  );
  projects.value = response.data;
}
onMounted(() => {
  getProjects();
});

/*projects.value = [
  "Steuererklärung",
  "Hauskauf",
  "Haustier adoptieren",
  "Heimkino",
];*/
</script>

<style scoped>
ion-list-header {
  text-align: center;
}
</style>