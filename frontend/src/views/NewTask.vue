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
        <ion-input placeholder="Enter task title" v-model="tTitle"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Task Description</ion-label>
        <ion-textarea placeholder="Enter information for task" v-model="tDescription"></ion-textarea>
      </ion-item>
      <ion-item>
        <ion-label>Assign to list</ion-label>
        <ion-select placeholder="Choose List" @ionChange="chosenList = $event.detail.value">
          <ion-select-option value="Unnassigned">Unassigned</ion-select-option>
          <ion-select-option value="Action">Actionlist</ion-select-option>
          <ion-select-option value="Project">Projectlist</ion-select-option>
        </ion-select>
      </ion-item>
      <ion-item v-if="chosenList === 'Project'">
        <ion-label>Select Projectlist</ion-label>
        <ion-select placeholder="Choose Projectlist" @ionChange="tlistId = $event.detail.value">
          <ion-select-option v-for="projectlist in projectlists" value="{{ projectlist.id }}" :key="projectlist.id">{{ projectlist.title }}</ion-select-option>
        </ion-select>
      </ion-item>
      <ion-item v-if="chosenList === 'Action'">
        <ion-label>Select Actionlist</ion-label>
        <ion-select placeholder="Choose Actionlist" @ionChange="tlistId = $event.detail.value">
          <ion-select-option v-for="actionlist in actionlists" value="{{ actionlist.id }}" :key="actionlist.id">{{ actionlist.title }}</ion-select-option>
        </ion-select>
      </ion-item>
        <ion-item>
          <ion-label>Select due date</ion-label>
                      <ion-datetime-button datetime="datetime"></ion-datetime-button>
        <ion-modal :keep-contents-mounted="true">
    <ion-datetime id="datetime" v-model="tDueDate"></ion-datetime>
  </ion-modal>
        </ion-item>
    </ion-list>
    <ion-item >
          <ion-buttons slot="end">
          <ion-button fill="solid" color="primary">Cancel</ion-button>
          <ion-button fill="solid" color="primary" @click="createTask(taskObject)">Save</ion-button>
          </ion-buttons>
          <p>{{ taskObject }}</p>
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
  IonSelectOption,
  IonSelect,
  IonTextarea
} from "@ionic/vue";
import { defineComponent, onMounted } from "vue";
import { ref } from "vue";
import { useProjectlists } from "@/composables/useProjectlists";
import {useActionlists} from "@/composables/useActionlists";
import { Projectlist } from "@/model/projectlist";
import { Task } from "@/model/task";
import { useTasks } from "../composables/useTasks";

const liststype = ref<any>([]);
liststype.value = ["Unassigned", "Action", "Project"];

const chosenList = ref("");

const {projectlists, getProjectlists} = useProjectlists();
const{actionlists, getActionlists} = useActionlists();
const {postedTask, createTask} = useTasks();


const tTitle = ref<string>("");
const tDescription = ref<string>("");
const tDueDate = ref<string>("");
const tStatus = ref<number>(0);
const tlistId = ref<any>(null);

if(chosenList.value === "Unassigned") {
  tlistId.value = null;
}

const taskObject = ref<Task>({
  "title": tTitle.value,
  "description": tDescription.value,
  "dueDate": tDueDate.value,
  "status": tStatus.value,
  "listId": tlistId.value
});
</script>