<template>
<ion-modal
          ref="modal"
          trigger="open-modal"
          @willDismiss="onWillDismiss"
        >
          <ion-header>
            <ion-toolbar>
              <ion-buttons slot="start">
                <ion-button @click="cancel()">Cancel</ion-button>
              </ion-buttons>
              <ion-title>{{taskDetails.title}}</ion-title>
              <ion-buttons slot="end">
                <ion-button :strong="true" @click="confirm()"
                  >Confirm</ion-button
                >
              </ion-buttons>
            </ion-toolbar>
          </ion-header>
          <ion-list>
            <ion-item>
              <ion-label >ID: </ion-label>
             <ion-label >{{taskDetails.id}}</ion-label>
             </ion-item>
             <ion-item>
              <ion-label >Created: </ion-label>
             <ion-label >{{taskDetails.created.substring(0, taskDetails.created.indexOf("T")) + " " + taskDetails.created.substring(11) + " Uhr"}}</ion-label>
             </ion-item>
             <ion-item>
              <ion-label >Description: </ion-label>
             <ion-label >{{taskDetails.description}}</ion-label>
             </ion-item>
             <ion-item>
              <ion-label >Due date: </ion-label>
             <ion-label >{{taskDetails.dueDate.substring(0, taskDetails.dueDate.indexOf("T")) + " " + taskDetails.dueDate.substring(11) + " Uhr"}}</ion-label>
             </ion-item>
             <ion-item>
              <ion-label >Status: </ion-label>
             <ion-label >{{taskDetails.status}}</ion-label>
             </ion-item>
           
             
            
            
          </ion-list>
            
        
          
           
        </ion-modal>
</template>

<script setup lang="ts">
import {
  IonModal,
  IonButtons,
  IonButton,
  IonItem,
  IonLabel,
  IonListHeader,
  IonList,
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
} from "@ionic/vue";
import axios from "axios";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";



const taskDetails = ref<any>([]);
const route = useRoute();
const id = route.params.id;

async function getTaskDetails() {
  const config = {
    headers: {
      "Content-Type": "application/json",
      "x-apikey": "5b2e750b0c346a20d90a5dda",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/tasks/" + id,
    config
  );
  taskDetails.value = response.data;
  console.log(taskDetails);
}
onMounted(() => {
  getTaskDetails();
});

</script>