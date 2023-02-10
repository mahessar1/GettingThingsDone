<template>
  <ion-page>
    <ion-tabs>
      <ion-router-outlet></ion-router-outlet>
      <ion-tab-bar slot="bottom">
        <ion-tab-button tab="overview" href="/tabs/overview">
          <ion-icon :icon="flash" />
          <ion-label>Tasks</ion-label>
        </ion-tab-button>

          
        <ion-tab-button tab="projectlists" href="/tabs/projectlists">
          <ion-icon :icon="trailSign" />
          <ion-label>Projects</ion-label>
        </ion-tab-button>
        
        <ion-tab-button tab="actionlists" href="/tabs/actionlists">
          <ion-icon :icon="walk" />
          <ion-label>Action</ion-label>
        </ion-tab-button>
      </ion-tab-bar>
    </ion-tabs>
  </ion-page>
</template>

<script setup lang="ts">
import { IonTabBar, IonTabButton, IonTabs, IonLabel, IonIcon, IonPage, IonRouterOutlet } from '@ionic/vue';
import { home, flash, trailSign, walk } from 'ionicons/icons';
</script>