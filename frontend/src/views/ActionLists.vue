<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Actionlists</ion-title>
        <ion-buttons slot="end">
          <ion-button
            fill="solid"
            color="primary"
            router-link="/tabs/newactionlist"
          >
            Add new Actionlist
            <ion-icon slot="end" :icon="addCircle"></ion-icon>
          </ion-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true" class="ion-padding">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Actionlists</ion-title>
        </ion-toolbar>
      </ion-header>

      <ion-card v-for="actionlist in actionlists" :key="actionlist">
        <ion-card-header>
          <ion-card-title>
            {{ actionlist.title }}
          <ion-icon v-if="actionlist.iconName === 'card'" :icon="card" size="large"></ion-icon>
          <ion-icon v-if="actionlist.iconName === 'call'" :icon="call" size="large"></ion-icon>
          <ion-icon v-if="actionlist.iconName === 'mail'" :icon="mail" size="large"></ion-icon>
          <ion-icon v-if="actionlist.iconName === 'bagHandle'" :icon="bagHandle" size="large"></ion-icon>
          <ion-icon v-if="actionlist.iconName === 'americanFootball'" :icon="americanFootball" size="large"></ion-icon>
          </ion-card-title>
        </ion-card-header>

        <ion-button fill="clear" router-link="/tabs/projecttask"
          >View Tasks
          <ion-icon slot="end" :icon="eye"></ion-icon>
        </ion-button>
        <ion-button fill="clear"
          >Edit Actionlist
          <ion-icon slot="end" :icon="pencil"></ion-icon>
        </ion-button>
        <ion-button
          fill="clear"
          color="danger"
          @click="presentAlert(actionlist.id)"
          >Delete Actionlist
          <ion-icon slot="end" :icon="trash"></ion-icon>
        </ion-button>
        <p>{{ handlerMessage }}</p>
        <p>{{ roleMessage }}</p>
      </ion-card>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCard,
  IonButton,
  IonIcon,
  alertController,
} from "@ionic/vue";
import { addCircle, eye, pencil, trash, checkmarkCircle, card, call, americanFootball, mail, bagHandle } from "ionicons/icons";
import axios from "axios";
import { onMounted, onUpdated, ref } from "vue";
import { useActionlists } from "../composables/useActionlists";

const handlerMessage = ref("");
const roleMessage = ref("");
const {actionlists, getActionlists, createActionlist, postedActionlist} = useActionlists();


const projects = ref<any>([]);


const presentAlert = async (id) => {
  const alert = await alertController.create({
    header: "Are you sure? All assigned task will set on Unassigned",
    buttons: [
      {
        text: "No",
        role: "cancel",
        handler: () => {
          handlerMessage.value = "Alert canceled";
        },
      },
      {
        text: "Yes",
        role: "confirm",
        handler: () => {
          axios.delete("http://localhost:8080/api/lists/actionlists/" + id);
          location.reload();
        },
      },
    ],
  });

  await alert.present();
};

async function getProjects() {
  const config = {
    headers: {
      "Content-Type": "application/json",
      "x-apikey": "5b2e750b0c346a20d90a5dda",
    },
  };
  const response = await axios.get(
    "http://localhost:8080/api/lists/projectlists",
    config
  );
  projects.value = response.data;
}
onMounted(() => {
  getProjects();
});

/*projects.value = [
  "Steuererklärung",
  "Hauskauf",
  "Haustier adoptieren",
  "Heimkino",
];*/
</script>

<style scoped>
ion-list-header {
  text-align: center;
}
</style>