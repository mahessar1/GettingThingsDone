<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Create new project</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-list>
      <ion-item>
        <ion-label>Project Name</ion-label>
        <ion-input placeholder="Enter project title"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Project Description</ion-label>
        <ion-input placeholder="Enter information for project"></ion-input>
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
import { Projectlist } from "@/model/projectlist";

const lists = ref<any>([]);
lists.value = ["Unassigned", "Action", "Project"];



const pTitle = ref<string>("");
const pDescription = ref<string>("");
const pPriority = ref<number>(0);
const pDue = ref<string>("");
const pStatus = ref<number>(0);

const projectlistObject = ref<Projectlist>({
  "listtype": "Projectlist",
  "title": pTitle.value,
  "description": pDescription.value,
  "priority": pPriority.value,
  "due": pDue.value,
  "status": pStatus.value
});
</script>