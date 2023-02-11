import { Actionlist, Projectlist } from '@/model/projectlist';
import { alertController } from '@ionic/vue';
import axios from 'axios'
import { onMounted, ref } from 'vue';

export function useProjectlists() {
    const projectlists = ref<Projectlist[]>([]);
    const postedProject = ref<Projectlist>();
    const finishedProject = ref<Projectlist>();
    const finishProjectObject = ref<any>();

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
        history.back();
    }

    async function finishProject(id:any) {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }
        const response = await axios.get('http://localhost:8080/api/lists/' + id, config);
        finishProjectObject.value = response.data;
        finishProjectObject.value.status = 2
        const response2 = await axios.put('http://localhost:8080/api/lists/projectlists/' + id, finishProjectObject.value, config);
        finishedProject.value = response2.data;
        location.reload();
    }

    onMounted(() => {
        getProjectlists();
    })

    return { projectlists, postedProject, finishedProject, getProjectlists, createProjectlist, finishProject }
}