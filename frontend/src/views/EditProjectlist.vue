<template>
  <ion-page>
    <ion-list>
      <ion-header>
        <ion-toolbar>
          <ion-title>Edit Project</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-item>
        <ion-label>Project Name</ion-label>
        <ion-input
          v-model="pTitle"
          placeholder="Enter project title"
        ></ion-input>
      </ion-item>
      <ion-item>
        <ion-label>Project Description</ion-label>
        <ion-textarea
          v-model="pDescription"
          placeholder="Enter information for project"
        ></ion-textarea>
      </ion-item>
      <ion-item>
        <ion-label>Set priority</ion-label>
        <ion-select
          placeholder="Choose priority"
          v-model="pPriority"
          @ionChange="pPriority = $event.detail.value"
        >
          <ion-select-option :value="1">Hoch</ion-select-option>
          <ion-select-option :value="2">Mittel</ion-select-option>
          <ion-select-option :value="3">Tief</ion-select-option>
        </ion-select>
      </ion-item>

      <ion-item>
        <ion-label>Select due date</ion-label>
        <ion-datetime-button datetime="datetime"></ion-datetime-button>
        <ion-modal :keep-contents-mounted="true">
          <ion-datetime
            @ionChange="realDueDate = pDue.split('+')[0]"
            id="datetime"
            v-model="pDue"
          ></ion-datetime>
        </ion-modal>
      </ion-item>
    </ion-list>
    <ion-item>
      <ion-buttons slot="end">
        <ion-button fill="solid" color="primary" @click="$router.go(-1)"
          >Cancel</ion-button
        >
        <ion-button
          fill="solid"
          color="primary"
          @click="createProjectlist(projectlistObject)"
          >Save</ion-button
        >
      </ion-buttons>
    </ion-item>
  </ion-page>
</template>
  
  <script setup lang="ts">

</script>