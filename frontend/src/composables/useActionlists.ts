import { Actionlist } from '@/model/projectlist';
import axios from 'axios'
import { onMounted, ref } from 'vue';


export function useActionlists() {
    const actionlists = ref<Actionlist[]>([]);

    async function getActionlists() {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }
        
        const response = await axios.get('http://localhost:8080/api/lists/actionlists', config);
        actionlists.value = response.data;
    }

    

    onMounted(() => {
        getActionlists();
    })

    return { actionlists, getActionlists }
}