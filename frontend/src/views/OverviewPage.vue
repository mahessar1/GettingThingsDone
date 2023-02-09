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
              <ion-page id="main-content">
                <ion-content class="ion-padding"> </ion-content>
              </ion-page>
              <ion-menu-toggle>
                <ion-button size="small" style="float: right">
                  Add new task
                  <ion-icon slot="end" :icon="addCircle"></ion-icon>
                </ion-button>
              </ion-menu-toggle>
            </ion-col>
          </ion-row>
        </ion-grid>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" >
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Overview</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-list v-for="task in tasks" v-bind:key="task">
        <ion-list-header lines="full">
          <ion-label>{{task.dueDate}}</ion-label>
        </ion-list-header>
        <ion-item button :router-link="'/tabs/taskdetails'">
          <ion-label>{{task.title}}</ion-label>
        </ion-item>
        
      </ion-list>
    </ion-content>
    <ion-menu :type="menuType" content-id="main-content">
      <ion-content class="ion-padding">
        <ion-toolbar>
          <ion-title>In which list you wanna add this task?</ion-title>
        </ion-toolbar>
        <ion-button>Add an undefined task</ion-button>
        <ion-menu-toggle>
          <ion-button>Close the menu</ion-button>
        </ion-menu-toggle>
      </ion-content>
    </ion-menu>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonMenuToggle,
  IonMenuButton,
  IonItem,
  IonLabel,
  IonListHeader,
  IonList,
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonButtons,
} from "@ionic/vue";
import { ref } from "vue";
import { addCircle } from "ionicons/icons";
import { useTasks } from "../composables/useTasks" 

const menuType = ref("overlay");

const { tasks, getTasks } = useTasks();

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

ion-list-header {
  text-align: center;
}
</style>