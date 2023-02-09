<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Create new project</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-list>
      <ion-item>
        <ion-datetime-button datetime="datetime"></ion-datetime-button>
        <ion-modal :keep-contents-mounted="true">
          <ion-datetime id="datetime"></ion-datetime>
        </ion-modal>
      </ion-item>
    </ion-list>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonDatetimeButton,
  IonModal,
  IonList,
} from "@ionic/vue";
import { defineComponent } from "vue";
</script>