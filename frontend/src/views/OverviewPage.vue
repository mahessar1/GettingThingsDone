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

      <ion-list v-for="date in dates" v-bind:key="date">
        <ion-list-header lines="full">
          <ion-label >{{ date }}</ion-label>
        </ion-list-header>
        <ion-item v-for="task in tasks" v-bind:key="task"  button :router-link="'/tabs/taskdetails'">
          <ion-label v-for="time in times" v-bind:key="time">{{ task.title }}</ion-label>
        </ion-item>
      </ion-list>
      <ion-button v-on:click="collectDates">Get Tasks</ion-button>
    </ion-content>
    <ion-menu :type="menuType" content-id="main-content">
      <ion-content class="ion-padding">
        <ion-toolbar>
          <ion-title>In which list you wanna add this task?</ion-title>
        </ion-toolbar>
        <ion-button>Add an undefined task</ion-button>
        <ion-menu-toggle>
          <ion-button>Close the menu</ion-button>
        </ion-menu-toggle>
      </ion-content>
    </ion-menu>
  </ion-page>
</template>

<script setup lang="ts">
import {
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
import { onMounted, ref } from "vue";
import { addCircle } from "ionicons/icons";
import { useTasks } from "../composables/useTasks";

const menuType = ref("overlay");
const { tasks, getTasks } = useTasks();

/*
const dates = ref([]);

const times = ref([]);
function collectDates() {
  let i;
  const localDateTimes = [];
  for (i = 0; i < tasks.value.length; i++) {
    localDateTimes.push(tasks.value[i].dueDate);
    if (
      !dates.value.includes(
        localDateTimes[i].substring(0, localDateTimes[i].indexOf("T"))
      )
    ) {
      dates.value.push(
        localDateTimes[i].substring(0, localDateTimes[i].indexOf("T"))
      );
    }
    times.value.push({"time": localDateTimes[i].substring(11),
                      "taskId": tasks.value[i].id});
  }
  console.log(dates, times,tasks);
}
*/

function collectDates() {
  const taskPerDate = ref<any>([]);
  const dates = ref<any>([]);
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