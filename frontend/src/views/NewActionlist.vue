<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Create new actionlist</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-list>
      <ion-item>
        <ion-label>Actionlist title</ion-label>
        <ion-input v-model="aTitle" placeholder="Enter actionlist title"></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Select Icon</ion-label>
        <ion-select placeholder="Choose the Icon" v-model="aIconname" @ionChange="aIconname = $event.detail.value">
          <ion-select-option value="call"><ion-icon :icon="call" size="large"></ion-icon>Phone</ion-select-option>
          <ion-select-option value="mail"><ion-icon :icon="mail" size="large"></ion-icon>Mail</ion-select-option>
          <ion-select-option value="card"><ion-icon :icon="card" size="large"></ion-icon>Finance</ion-select-option>
          <ion-select-option value="americanFootball"><ion-icon :icon="americanFootball" size="large"></ion-icon>Sports</ion-select-option>
          <ion-select-option value="bagHandle"><ion-icon :icon="bagHandle" size="large"></ion-icon>Groceries</ion-select-option>
        </ion-select>
      </ion-item>
    </ion-list>
    <ion-item>
      <ion-buttons slot="end">
        <ion-button
          fill="solid"
          color="primary"
          @click="$router.go(-1)"
          >Cancel</ion-button
        >
        <ion-button
          fill="solid"
          color="primary"
          @click="createActionlist(actionlistObject)"
          >Save</ion-button
        >
      </ion-buttons>
      <p>{{ actionlistObject }}</p>
    </ion-item>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonToolbar,
  IonTitle,
  IonPage,
  IonInput,
  IonHeader,
  IonItem,
  IonLabel,
  IonList,
  IonSelect,
  IonSelectOption,
  IonIcon
} from "@ionic/vue";
import { ref } from "vue";
import { Actionlist } from "@/model/projectlist";
import { call, mail, card, americanFootball, bagHandle } from 'ionicons/icons';
import { useActionlists } from "../composables/useActionlists";

const { createActionlist } = useActionlists();


const aTitle = ref<any>("");
const aIconname = ref<any>("");

/*
const pTitle = ref<string>("");
const pDescription = ref<string>("");
const pPriority = ref<number>(0);
const pDue = ref<string>("");
const pStatus = ref<number>(0);*/


const actionlistObject = ref<Actionlist>({
  "listtype": "Actionlist",
  "title": aTitle,
  "iconName": aIconname
});
</script>