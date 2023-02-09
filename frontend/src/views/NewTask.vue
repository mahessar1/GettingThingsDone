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
      <ion-input></ion-input>
    </ion-item>

    <ion-item>
      <ion-label>Task Description</ion-label>
      <ion-input placeholder="Enter company name"></ion-input>
    </ion-item>

      <ion-item>
        <ion-label>Assign to list</ion-label>
      <ion-select placeholder="Choose List">
        <ion-select-option value="Action">Action</ion-select-option>
        <ion-select-option value="Project">Project</ion-select-option>
      </ion-select>
    </ion-item>

    <ion-item>
      <ion-label>Due Date & Time</ion-label>
      <ion-datetime></ion-datetime>    
          </ion-item>

  </ion-list>
</ion-page>
</template>

<script lang="ts">
  import { IonToolbar, IonTitle, IonPage, IonInput, IonHeader, IonDatetime, IonItem, IonLabel, IonList } from '@ionic/vue';
  import { defineComponent } from 'vue';
  import { ref } from "vue";

  export default defineComponent({
    components: { IonInput, IonItem, IonLabel, IonList },
  });

  const lists = ref<any>([]);
lists.value = [
  "Action",
  "Project",
];
</script>