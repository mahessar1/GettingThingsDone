import { Actionlist, Projectlist } from '@/model/projectlist';
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

    return { projectlists, postedProject, getProjectlists, createProjectlist }
}