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
    <ion-content  :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Overview</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-list v-for="(tasks) in taskPerDate" v-bind:key="tasks">
        <ion-list-header  lines="full">
          <ion-label>{{tasks.date}}</ion-label>
        </ion-list-header>
        <ion-item v-for="(task) in tasks.tasklist" v-bind:key="task" >
      
          <ion-label >{{"Due date: " + task.dueDate.substring(11) + "Uhr /////////// task title: " + task.title }}</ion-label>
          <ion-button :id="task.id" expand="block">Details</ion-button>
          <task-details :taskId="task.id"></task-details>
        </ion-item>
        
      </ion-list>
    </ion-content>
    <ion-menu :type="menuType" content-id="main-content">
      <ion-content class="ion-padding">
        <ion-toolbar>
          <ion-title>Create a task/project</ion-title>
        </ion-toolbar>
        <ion-button router-link="/tabs/newtask">Create new task</ion-button>
        <ion-button router-link="/tabs/newproject">Creae new project</ion-button>
        <ion-menu-toggle>
    
          <ion-button color="danger">Close the menu</ion-button>
        </ion-menu-toggle>
      </ion-content>
    </ion-menu>
  </ion-page>
</template>

<script setup lang="ts">
import {
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
} from "@ionic/vue";
import { onMounted, onUpdated, ref } from "vue";
import { addCircle } from "ionicons/icons";
import { useTasks } from "../composables/useTasks";
import TaskDetails from "../components/TaskDetails.vue"


const menuType = ref("overlay");
const { tasks, getTasks, collectDates, taskPerDate } = useTasks();
onUpdated(() => {
  collectDates();
})

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