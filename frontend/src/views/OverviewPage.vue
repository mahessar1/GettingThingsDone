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
                  <ion-icon slot="end" :icon="addCircle"></ion-icon>
                  Add
                  
                </ion-button>
              </ion-menu-toggle>
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

      <ion-list v-for="tasks in taskPerDate" v-bind:key="tasks">
        <ion-list-header lines="full">
          <ion-label>{{ tasks.date }}</ion-label>
        </ion-list-header>
<ion-card  v-for="task in tasks.tasklist" v-bind:key="task">
  <ion-card-header>
    <ion-card-title>{{task.title}} <ion-button v-if="task.status == 1"
              fill="solid"
              color="success"
              size="small"
              style="float: right"
            >
              Open
              <ion-icon slot="end"></ion-icon>
            </ion-button>
            <ion-button v-if="task.status == 2"
              fill="solid"
              color="success"
              size="small"
              style="float: right"
            >
              In progress
              <ion-icon slot="end"></ion-icon>
            </ion-button></ion-card-title>
    
    <ion-card-subtitle>{{"Due time: " + task.dueDate.substring(11) + " Uhr"}}</ion-card-subtitle>
  </ion-card-header>

  <ion-card-content>
    <ion-button fill="clear"  :id="task.id">
      <task-details :taskId="task.id"></task-details>
      <ion-icon slot="end" :icon="eye"></ion-icon>
      View Task</ion-button>
       <ion-button fill="clear" :router-link="'edittask/'+task.id">Edit Task
          <ion-icon slot="end" :icon="pencil"></ion-icon>
        </ion-button>
        <ion-button
          fill="clear"
          color="danger"
          @click="alert(task.id)"
          >Delete Task
          <ion-icon slot="end" :icon="trash"></ion-icon>
        </ion-button>
        
  </ion-card-content>
 <p>{{ handlerMessage }}</p>
        <p>{{ roleMessage }}</p>
</ion-card>
      </ion-list>
    </ion-content>
    <ion-menu :type="menuType" content-id="main-content">
      <ion-content class="ion-padding">
        <ion-toolbar>
          <ion-title>Create a task/project</ion-title>
        </ion-toolbar>
        <ion-button router-link="/tabs/newtask">Create new task</ion-button>
        <ion-button router-link="/tabs/newproject"
          >Create new project</ion-button
        >
        <ion-menu-toggle>
          <ion-button color="danger">Close the menu</ion-button>
        </ion-menu-toggle>
        
      </ion-content>
    </ion-menu>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardSubtitle,
  IonCardContent,
  IonBackButton,
  IonModal,
  IonCol,
  IonButton,
  IonRow,
  IonMenu,
  IonGrid,
  IonIcon,
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
  alertController,
} from "@ionic/vue";
import { onMounted, onUpdated, ref } from "vue";
import { addCircle, eye, pencil, trash, checkmarkCircle } from "ionicons/icons";
import { useTasks } from "../composables/useTasks";
import axios from "axios";
import TaskDetails from "../components/TaskDetails.vue";


const menuType = ref("overlay");
const { tasks, getTasks, collectDates, taskPerDate, alert } = useTasks();
const handlerMessage = ref("");
const roleMessage = ref("");

onUpdated(() => {
  collectDates();
});

window.onpopstate = function () {
  location.reload();
};



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