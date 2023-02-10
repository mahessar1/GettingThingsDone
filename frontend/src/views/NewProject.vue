<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Create new project</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-list>
      <ion-item>
        <ion-label>Project title</ion-label>
        <ion-input v-model="pTitle" placeholder="Enter project title"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Project description</ion-label>
        <ion-input v-model="pDescription" placeholder="Enter information for project"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Set priority</ion-label>
        <ion-select placeholder="Choose priority" @ionChange="pPriority = $event.detail.value">
          <ion-select-option value="1">Hoch</ion-select-option>
          <ion-select-option value="2">Mittel</ion-select-option>
          <ion-select-option value="3">Tier</ion-select-option>
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
    <ion-item>
      <ion-buttons slot="end">
        <ion-button
          fill="solid"
          color="primary"
          router-link="/tabs/projectlists"
          >Cancel</ion-button
        >
        <ion-button
          fill="solid"
          color="primary"
          @click="createProjectlist(projectlistObject)"
          >Save</ion-button
        >
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
import { useProjectlists } from "@/composables/useProjectlists";

const { postedProject, createProjectlist } = useProjectlists();


const pTitle = ref<any>("");
const pDescription = ref<any>("");
const pPriority = ref<any>(0);
const pDue = ref<any>("");
const pStatus = ref<any>(0);

/*
const pTitle = ref<string>("");
const pDescription = ref<string>("");
const pPriority = ref<number>(0);
const pDue = ref<string>("");
const pStatus = ref<number>(0);*/


const projectlistObject = ref<Projectlist>({
  "listtype": "Projectlist",
  "title": pTitle,
  "description": pDescription,
  "priority": pPriority,
  "due": pDue,
  "status": pStatus,
});
</script>