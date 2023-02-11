<template>
  <ion-page>
  <ion-list>
      <ion-header>
    <ion-toolbar>
      <ion-title>Edit Project</ion-title>
    </ion-toolbar>
  </ion-header>
    <ion-item>
      <ion-label>Project title</ion-label>
      <ion-input v-model="pTitle" placeholder="Enter project title"></ion-input>
    </ion-item>
    <ion-item>
      <ion-label>Project description</ion-label>
      <ion-textarea
        placeholder="Enter information for project"
        v-model="pDescription"
      ></ion-textarea>
    </ion-item>
    <ion-item>
      <ion-label>Set priority</ion-label>
      <ion-select placeholder="Choose priority" v-model="pPriority" @ionChange="pPriority = $event.detail.value">
          <ion-select-option :value="1">Hoch</ion-select-option>
          <ion-select-option :value="2">Mittel</ion-select-option>
          <ion-select-option :value="3">Tief</ion-select-option>
        </ion-select>
    </ion-item>
    <ion-item>
        <ion-label>Select due date</ion-label>
        <ion-datetime-button datetime="datetime"></ion-datetime-button>
        <ion-modal :keep-contents-mounted="true">
          <ion-datetime @ionChange="realDueDate = pDue.split('+')[0]" id="datetime" v-model="pDue"></ion-datetime>
        </ion-modal>
      </ion-item>
  </ion-list>
  <ion-item>
    <ion-buttons slot="end">
      <ion-button fill="solid" color="primary" @click="$router.go(-1)"
        >Cancel</ion-button
      >
      <ion-button fill="solid" color="primary" @click="editProject(pId)"
        >Save</ion-button
      >
    </ion-buttons>
    <p>{{ projectObject }}</p>
  </ion-item>
</ion-page>
</template>

<script setup lang="ts">
import {
IonToolbar,
IonInput,
IonDatetimeButton,
IonPage,
IonTitle,
IonHeader,
IonDatetime,
IonItem,
IonLabel,
IonList,
IonSelectOption,
IonSelect,
IonTextarea,
IonButton,
IonButtons,
IonModal
} from "@ionic/vue";
import { onMounted, onUpdated } from "vue";
import { ref } from "vue";
import { useTasks } from "../composables/useTasks";
import { useRoute } from "vue-router";
import axios from 'axios';


const project = ref<any>();
const postedProject = ref<any>();

const pId = ref<any>(0)
const pTitle = ref<any>("");
const pDescription = ref<any>("");
const pPriority = ref <any>(null);
const pDue = ref<any>("");
const pStatus = ref<any>(1);
const realDueDate = ref<any>("");



const projectObject = ref<any>({
"title": pTitle.value,
"description": pDescription.value,
"priority": pPriority.value,
"due": realDueDate.value,
"status": pStatus.value
});

async function getProjectById(projectId: any) {

      const config = {
          headers: {
              "Content-Type": "application/json",
          }
      }
      const response = await axios.get('http://localhost:8080/api/lists/' + projectId, config);
      project.value = response.data;

      pId.value = project.value.id;
      pTitle.value = project.value.title;
      pDescription.value = project.value.description;
      pPriority.value = project.value.priority;
      pDue.value = project.value.due;
      pStatus.value = project.value.status;

  }

  async function editProject(projectId: any) {
      const config = {
          headers: {
              "Content-Type": "application/json"
          }
      }
      projectObject.value.title = pTitle.value;
      projectObject.value.description = pDescription.value;
      projectObject.value.priority = parseInt(pPriority.value);
      projectObject.value.due = realDueDate.value;
      projectObject.value.status = parseInt(pStatus.value);
      

      const response = await axios.put('http://localhost:8080/api/lists/projectlists/' + projectId, projectObject.value, config);
      postedProject.value = response.data;

      history.back();
  }

onMounted(() => {
const projectId = ref(useRoute().params.id);
getProjectById(projectId.value);
});
onUpdated(() => {
const projectId = ref(useRoute().params.id);
getProjectById(projectId.value);
});
</script>