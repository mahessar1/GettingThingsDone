<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Projects</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="ion-padding">
    <ion-fab slot="fixed" vertical="top" horizontal="end" :edge="true">
      <ion-fab-button>
        <ion-icon :icon="add"></ion-icon>
      </ion-fab-button>
      <ion-fab-list side="bottom">
        <ion-fab-button>
          <ion-icon :icon="trailSign" />
        </ion-fab-button>
        <ion-fab-button>
          <ion-icon :icon="flash" />
        </ion-fab-button>
      </ion-fab-list>
    </ion-fab>
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Projects</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-card v-for="project in projects" v-bind:key="project">
        <ion-card-header>
          <ion-card-title>{{ project }}</ion-card-title>
        </ion-card-header>

        <ion-card-content> Project Description </ion-card-content>

        <ion-button fill="clear">View Tasks</ion-button>
        <ion-button fill="clear">Delete Project</ion-button>
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
} from "@ionic/vue";
import { ref } from "vue";
import { add, flash, trailSign} from 'ionicons/icons';

const projects = ref<any>([]);
projects.value = [
  "Steuererklärung",
  "Hauskauf",
  "Haustier adoptieren",
  "Heimkino",
];
</script>