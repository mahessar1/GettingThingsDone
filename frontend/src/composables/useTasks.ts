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

    return { tasks, getTasks }
}



