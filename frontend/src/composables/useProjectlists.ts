import { Actionlist, Projectlist } from '@/model/projectlist';
import { alertController } from '@ionic/vue';
import axios from 'axios'
import { onMounted, ref } from 'vue';

export function useProjectlists() {
    const projectlists = ref<Projectlist[]>([]);
    const postedProject = ref<Projectlist>();

    async function getProjectlists() {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }
        const response = await axios.get('http://localhost:8080/api/lists/projectlists', config);
        projectlists.value = response.data;

    }

    async function createProjectlist(projectlist: Projectlist) {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }

        const response = await axios.post('http://localhost:8080/api/lists/projectlists', projectlist, config);
        postedProject.value = response.data;
    }

    onMounted(() => {
        getProjectlists();
    })

    async function editProjectList(project: Projectlist) {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }

        const response = await axios.put('http://localhost:8080/api/tasks', project, config);
        postedProject.value = response.data;

        presentAlert(postedProject.value)
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

    return { projectlists, postedProject, getProjectlists, createProjectlist, editProjectList, presentAlert }
}
}