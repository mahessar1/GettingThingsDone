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
        <ion-item v-for="(task) in tasks.tasklist" v-bind:key="task" button :router-link="'/tabs/taskdetails/' + task.id">
      
          <ion-label >{{"Due date: " + task.dueDate.substring(11) + "Uhr /////////// task title: " + task.title }}</ion-label>
          <ion-button id="open-modal" expand="block">Details</ion-button>
          
        </ion-item>
      </ion-list>
      <p v-if="show">
        <ion-button v-on:click="collectDates(), hidden()">Get Tasks</ion-button>
      </p>
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



const menuType = ref("overlay");
const { tasks, getTasks } = useTasks();
const taskPerDate = ref<any>([]);
  const dates = ref<any>([]);
let show = true;
function hidden() {
  show = false;
}

function collectDates() {
  
  (tasks.value).forEach(task => {
    if(!(dates.value.includes(task.dueDate.substring(0, task.dueDate.indexOf("T"))))) {
      dates.value.push(task.dueDate.substring(0, task.dueDate.indexOf("T")));
    }
  });
  console.log(dates.value);
  
  (dates.value).forEach(date => {
      const taskList = ref<any>([]);
      for (let i = 0; i < tasks.value.length; i++) {
        if (tasks.value[i].dueDate.substring(0, tasks.value[i].dueDate.indexOf("T")) == date) {
        taskList.value.push(tasks.value[i]);
      }
      }
      
      taskPerDate.value.push({
          "date": date,
          "tasklist": taskList

        })
      
    });
    
  console.log(taskPerDate.value);
}

onMounted(() => {
  collectDates();

  
});
onUpdated(() => {
  taskPerDate.value = [];
  collectDates();
  
})
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