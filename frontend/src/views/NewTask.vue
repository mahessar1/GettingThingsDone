<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Create new task</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-list>
      <ion-item>
        <ion-label>Task Name</ion-label>
        <ion-input placeholder="Enter task title"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Task Description</ion-label>
        <ion-input placeholder="Enter information for task"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Assign to list</ion-label>
        <ion-select placeholder="Choose List">
          <ion-select-option value="Unassigned">Unassigned</ion-select-option>
          <ion-select-option value="Action">Action</ion-select-option>
          <ion-select-option value="Project">Project</ion-select-option>
        </ion-select>
      </ion-item>
        <ion-item>
          <ion-label>Select due date</ion-label>
                      <ion-datetime-button datetime="datetime"></ion-datetime-button>
        <ion-modal :keep-contents-mounted="true">
    <ion-datetime id="datetime"></ion-datetime>
  </ion-modal>
        </ion-item>
    </ion-list>
    <ion-item >
          <ion-buttons slot="end">
          <ion-button fill="solid" color="primary">Cancel</ion-button>
          <ion-button fill="solid" color="primary">Save</ion-button>
          </ion-buttons>
    </ion-item>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonToolbar,
  IonDatetimeButton,
  IonModal,
  IonTitle,
  IonPage,
  IonInput,
  IonHeader,
  IonDatetime,
  IonItem,
  IonLabel,
  IonList,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { ref } from "vue";

const lists = ref<any>([]);
lists.value = ["Unassigned", "Action", "Project"];
</script>