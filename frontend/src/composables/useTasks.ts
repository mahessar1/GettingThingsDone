import { Task } from '@/model/task';
import axios from 'axios'
import { onMounted, ref } from 'vue';


export function useTasks() {

    const tasks = ref<Task[]>([]);

   async function getTasks() {
        const config = {
            headers: {
                "Content-Type": "application/json",
                "x-apikey": "5b2e750b0c346a20d90a5dda",
            }
        }
        const response = await axios.get('http://localhost:8080/api/tasks', config);
        tasks.value = response.data;
    } 

         

    onMounted(() => {
        getTasks();
    })

    const projectTasks = ref<Task[]>([]);

    async function getProjectTasks() {

        
         const config = {
             headers: {
                 "Content-Type": "application/json",
                 "x-apikey": "5b2e750b0c346a20d90a5dda",
             }
         }
        const response = await axios.get('http://localhost:8080/api/lists/{id}/tasks',config);
        projectTasks.value = response.data;  
         

    }
    onMounted(() => {
        getProjectTasks();
    })


    return { tasks, getTasks, projectTasks, getProjectTasks }

   
}



