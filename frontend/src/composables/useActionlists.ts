import { Actionlist } from '@/model/projectlist';
import axios from 'axios'
import { onMounted, ref } from 'vue';
import { alertController } from '@ionic/vue';


export function useActionlists() {
    const actionlists = ref<Actionlist[]>([]);
    const postedActionlist = ref<Actionlist>();

    async function getActionlists() {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }
        
        const response = await axios.get('http://localhost:8080/api/lists/actionlists', config);
        actionlists.value = response.data;
    }

    async function createActionlist(actionlist: Actionlist) {
        const config = {
            headers: {
                "Content-Type": "application/json"
            }
        }

        const response = await axios.post('http://localhost:8080/api/lists/actionlists', actionlist, config);
        postedActionlist.value = response.data;

        presentAlert(postedActionlist)
    }

    const presentAlert = async (actionlist: any) => {
        const alert = await alertController.create({
            header: "Actionlist with the Title " + actionlist.title + "has been created",
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
    

    onMounted(() => {
        getActionlists();
    })

    return { actionlists, getActionlists, createActionlist, postedActionlist }
}