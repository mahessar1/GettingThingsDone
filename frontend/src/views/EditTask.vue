<template>
    <ion-page>
    <ion-list>
        <ion-header>
      <ion-toolbar>
        <ion-title>Edit Task</ion-title>
      </ion-toolbar>
    </ion-header>
      <ion-item>
        <ion-label>Task Name</ion-label>
        <ion-input v-model="tTitle" placeholder="Enter task title"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Task Description</ion-label>
        <ion-textarea
          placeholder="Enter information for task"
          v-model="tDescription"
        ></ion-textarea>
      </ion-item>
      <ion-item>
        <ion-label>Assign to list</ion-label>
        <ion-select
          placeholder="Choose List"
          @ionChange="chosenList = $event.detail.value"
        >
          <ion-select-option value="Unnassigned">Unassigned</ion-select-option>
          <ion-select-option value="Action">Actionlist</ion-select-option>
          <ion-select-option value="Project">Projectlist</ion-select-option>
        </ion-select>
      </ion-item>
      <ion-item v-if="chosenList === 'Project'">
        <ion-label>Select Projectlist</ion-label>
        <ion-select
          placeholder="Choose Projectlist"
          @ionChange="tList = $event.detail.value"
        >
          <ion-select-option
            v-for="projectlist in projectlists"
            :value="projectlist.id"
            :key="projectlist.id"
            >{{ projectlist.title }}</ion-select-option
          >
        </ion-select>
      </ion-item>
      <ion-item v-if="chosenList === 'Action'">
        <ion-label>Select Actionlist</ion-label>
        <ion-select
          placeholder="Choose Actionlist"
          @ionChange="tList = $event.detail.value"
          :selected-text="tList"
        >
          <ion-select-option
            v-for="actionlist in actionlists"
            :value="actionlist.id"
            :key="actionlist.id"
            >{{ actionlist.title }}</ion-select-option
          >
        </ion-select>
      </ion-item>
      <ion-item>
        <ion-label>Select due date</ion-label>
        <ion-datetime-button datetime="datetime"></ion-datetime-button>
        <ion-modal :keep-contents-mounted="true">
          <ion-datetime
            @ionChange="realDueDate = tDueDate.split('+')[0]"
            id="datetime"
            v-model="tDueDate"
          ></ion-datetime>
        </ion-modal>
      </ion-item>
    </ion-list>
    <ion-item>
      <ion-buttons slot="end">
        <ion-button fill="solid" color="primary" @click="$router.go(-1)"
          >Cancel</ion-button
        >
        <ion-button fill="solid" color="primary" @click="editTask(tId)"
          >Save</ion-button
        >
      </ion-buttons>
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
import { useProjectlists } from "@/composables/useProjectlists";
import { useActionlists } from "@/composables/useActionlists";
import { useTasks } from "../composables/useTasks";
import { useRoute } from "vue-router";
import axios from 'axios';


const liststype = ref<any>("");

const chosenList = ref("");

const { projectlists, getProjectlists } = useProjectlists();
const { actionlists, getActionlists } = useActionlists();
const { presentAlert } = useTasks();

const task = ref<any>();
const postedTask = ref<any>();

const tId = ref<any>(0)
const tTitle = ref<any>("");
const tDescription = ref<any>("");
const tDueDate = ref<any>("");
const tStatus = ref<any>(1);
const tList = ref<any>(null);
const realDueDate = ref<any>("");



const taskObject = ref<any>({
  "title": tTitle.value,
  "description": tDescription.value,
  "status": tStatus.value,
  "dueDate": realDueDate.value,
  "listId": tList.value,
});

async function getTaskById(taskId: any) {
  
        const config = {
            headers: {
                "Content-Type": "application/json",
            }
        }
        const response = await axios.get('http://localhost:8080/api/tasks/' + taskId, config);
        task.value = response.data;
        tId.value = task.value.id;
        tTitle.value = task.value.title;
        tDescription.value = task.value.description;
        tStatus.value = task.value.status;
        tList.value = task.value.lists.id;


projectlists.value.forEach((projectlist) => {
  if (projectlist.id === tId.value) {
    liststype.value = "Project";
  } else {
    actionlists.value.forEach((actionlist) => {
      if (actionlist.id === tId.value) {
        liststype.value = "Action";
      } else {
        liststype.value = "Unassigned";
      }
    });
  }
});
    }

    async function editTask(taskId: any) {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }
        taskObject.value.title = tTitle.value;
        taskObject.value.description = tDescription.value;
        taskObject.value.status = parseInt(tStatus.value);
        taskObject.value.dueDate = realDueDate.value;
        taskObject.value.listId = parseInt(tList.value);
        

        const response = await axios.put('http://localhost:8080/api/tasks/' + taskId, taskObject.value, config);
        postedTask.value = response.data;

        history.back();
    }

onMounted(() => {
  const taskId = ref(useRoute().params.id);
getTaskById(taskId.value);
});
onUpdated(() => {
  const taskId = ref(useRoute().params.id);
getTaskById(taskId.value);
});
</script>