<template>
  <ion-modal ref="modal" :trigger="props.taskId">
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
          :selected-text="liststype"
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
          :selected-text="tList"
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
        <ion-button fill="solid" color="primary" @click="editTask(taskObject)"
          >Save</ion-button
        >
      </ion-buttons>
      <p>{{ taskObject }}</p>
    </ion-item>
  </ion-modal>
</template>
  
  <script setup lang="ts">
import {
  IonToolbar,
  IonInput,
  IonDatetimeButton,
  IonModal,
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
} from "@ionic/vue";
import { defineComponent, onMounted, onRenderTriggered } from "vue";
import { ref } from "vue";
import { useProjectlists } from "@/composables/useProjectlists";
import { useActionlists } from "@/composables/useActionlists";
import { Task } from "@/model/task";
import { useTasks } from "../composables/useTasks";

const props = defineProps({
  taskId: Number,
});

const task = ref<any>();

const liststype = ref<any>("");

const chosenList = ref("");

const { projectlists, getProjectlists } = useProjectlists();
const { actionlists, getActionlists } = useActionlists();
const { getTaskById, editTask } = useTasks();

const tId = ref<any>(task.value.id);
const tTitle = ref<any>(task.value.title);
const tDescription = ref<any>(task.value.description);
const tDueDate = ref<any>(task.value.dueDate);
const tStatus = ref<any>(task.value.status);
const tList = ref<any>(task.value.lists.id);
const realDueDate = ref<any>("");

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

const taskObject = ref<Task>({
  id: tId,
  title: tTitle,
  description: tDescription,
  status: tStatus,
  dueDate: realDueDate,
  listId: tList,
});

onRenderTriggered(() => {
  task.value = getTaskById(props.taskId);
});
</script>