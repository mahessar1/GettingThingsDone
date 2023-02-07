<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-grid>
          <ion-row>
            <ion-col>
              <ion-title>Overview</ion-title>
            </ion-col>
            <ion-col>
              <ion-button style="float: right">Neuer Task</ion-button>
            </ion-col> 
          </ion-row>
        </ion-grid>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Overview</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-list>
        <ion-list-header lines="full">
          <ion-label>13.02.2023</ion-label>
        </ion-list-header>
        <ion-item button :router-link="'/tabs/taskdetails'">
          <ion-label>FSWD Projekt abschliessen</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Git-Link einreichen</ion-label>
        </ion-item>
        <ion-list-header lines="full">
          <ion-label>14.02.2023</ion-label>
        </ion-list-header>
        <ion-item button>
          <ion-label>Leben geniessen</ion-label>
        </ion-item>
      </ion-list>
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
</script>

<style>
ion-list-header {
  font-size: 25px;
  font-weight: bold;
  text-align: center;
}
ion-item {
  font-size: 15px;
}
</style>