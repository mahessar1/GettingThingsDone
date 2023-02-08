<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Tasks</ion-title>
        <ion-buttons slot="primary">
          <ion-button fill="solid" color="primary">
            Add new Task
            <ion-icon slot="end" :icon="addCircle"></ion-icon>
          </ion-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <!--Es wird immer eine Liste pro Datum erstellt (For Each)-->
      <ion-list>
        <ion-list-header lines="full">
          <ion-label>13.02.2023</ion-label>
        </ion-list-header>
        <ion-item
          button
          :router-link="'/tabs/tasks/' + 'task.id'"
          lines="inset"
        >
          <ion-grid>
            <ion-row>
              <ion-col>
                <ion-label>Invite friends</ion-label>
              </ion-col>

              <ion-col>
                <ion-label>Actionlist: Calls</ion-label>
              </ion-col>

              <ion-col>
                <ion-label>Cash</ion-label>
              </ion-col>
            </ion-row>
          </ion-grid>
        </ion-item>
        <ion-item>
          <ion-label>Task 2</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Task 3</ion-label>
        </ion-item>
        <ion-item>
          <ion-label>Task 4</ion-label>
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
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCol,
  IonRow,
  IonGrid,
  IonItem,
  IonList,
  IonButton,
  IonInput,
  IonButtons,
} from "@ionic/vue";
import { ref } from "vue";
import { addCircle } from "ionicons/icons";
import { useTodos } from "../composables/useTodos";

const menuType = ref("overlay");

const { newTodo, todos, getTodos, addTodo, finishTodo, archiveTodo } =
  useTodos();
</script>

<style scoped>
ion-col {
  text-align: center;
}

ion-list-header {
  text-align: center;
}
</style>