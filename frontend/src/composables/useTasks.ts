import { Task } from '@/model/task';
import axios from 'axios'
import { onMounted, ref } from 'vue';
import { alertController } from '@ionic/vue';


export function useTasks() {

    const tasks = ref<Task[]>([]);
    const postedTask = ref<Task>();
    const taskPerDate = ref<any>([]);
  const dates = ref<any>([]);

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

    async function createTask(task: Task) {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }

        const response = await axios.post('http://localhost:8080/api/tasks', task, config);
        postedTask.value = response.data;

        presentAlert(postedTask.value)
    }





    const presentAlert = async (task: any) => {
        const alert = await alertController.create({
            header: "Task with the Title " + task.title + ((task.lists === null) ? "has been created and is Unassigned" : " has been created in the list: " + task.lists.title),
            buttons: [
                {
                    text: "Okay!",
                    handler: () => {
                        history.back();
                    },
                },
            ],
        });

        await alert.present();
    };

    async function collectDates() {
        await getTasks();
        taskPerDate.value = [];
  
        (tasks.value).forEach(task => {
          if(!(dates.value.includes(task.dueDate.substring(0, task.dueDate.indexOf("T"))))) {
            dates.value.push(task.dueDate.substring(0, task.dueDate.indexOf("T")));
          }
        });
        console.log(dates.value);
        
        (dates.value).forEach((date: string) => {
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
        getTasks();
        collectDates();
    })

    /* const projectTasks = ref<Task[]>([]);
 
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
 */

    return { tasks, getTasks, createTask, postedTask, collectDates, taskPerDate }


}



