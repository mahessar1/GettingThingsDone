<template>
  <ion-page>
    <ion-list>
      <ion-header>
        <ion-toolbar>
          <ion-title>Edit Actionlist</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-item>
        <ion-label>Actionlist Name</ion-label>
        <ion-input
          v-model="aTitle"
          placeholder="Enter actionlist title"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Select Icon</ion-label>
        <ion-select
          placeholder="Choose the Icon"
          v-model="aIconname"
          @ionChange="aIconname = $event.detail.value"
        >
          <ion-select-option value="call"
            ><ion-icon :icon="call" size="large"></ion-icon
            >Phone</ion-select-option
          >
          <ion-select-option value="mail"
            ><ion-icon :icon="mail" size="large"></ion-icon
            >Mail</ion-select-option
          >
          <ion-select-option value="card"
            ><ion-icon :icon="card" size="large"></ion-icon
            >Finance</ion-select-option
          >
          <ion-select-option value="americanFootball"
            ><ion-icon :icon="americanFootball" size="large"></ion-icon
            >Sports</ion-select-option
          >
          <ion-select-option value="bagHandle"
            ><ion-icon :icon="bagHandle" size="large"></ion-icon
            >Groceries</ion-select-option
          >
        </ion-select>
      </ion-item>
      <ion-item>
        <ion-buttons slot="end">
          <ion-button fill="solid" color="primary" @click="$router.go(-1)"
            >Cancel</ion-button
          >
          <ion-button fill="solid" color="primary" @click="editActionlist(aId)"
            >Save</ion-button
          >
        </ion-buttons>
      </ion-item>
    </ion-list>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonToolbar,
  IonInput,
  IonPage,
  IonTitle,
  IonHeader,
  IonItem,
  IonLabel,
  IonList,
  IonSelectOption,
  IonSelect,
  IonButton,
  IonButtons,
} from "@ionic/vue";
import { onMounted, onUpdated } from "vue";
import { ref } from "vue";
import { call, mail, card, americanFootball, bagHandle } from "ionicons/icons";
import { useTasks } from "../composables/useTasks";
import { useRoute } from "vue-router";
import axios from "axios";

const actionlist = ref<any>();
const postedActionlist = ref<any>();

const aId = ref<any>(0);
const aTitle = ref<any>("");
const aIconname = ref<any>("");

const actionlistObject = ref<any>({
  title: aTitle.value,
  iconName: aIconname.value,
});

async function getTaskById(actionId: any) {
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/lists/" + actionId,
    config
  );
  actionlist.value = response.data;

  aId.value = actionlist.value.id;
  aTitle.value = actionlist.value.title;
  aIconname.value = actionlist.value.iconname;
}

async function editActionlist(actionlistId: any) {
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  actionlistObject.value.title = aTitle.value;
  actionlistObject.value.iconName = aIconname.value;

  const response = await axios.put(
    "http://localhost:8080/api/lists/actionlists/" + actionlistId,
    actionlistObject.value,
    config
  );
  postedActionlist.value = response.data;

  history.back();
}

onMounted(() => {
  const actionId = ref(useRoute().params.id);
  getTaskById(actionId.value);
});
onUpdated(() => {
  const actionId = ref(useRoute().params.id);
  getTaskById(actionId.value);
});
</script>