import { useState } from "react"
import useFetch from "../hooks/useFetch";
import {useHistory} from 'react-router-dom';



const CreateAnimal = ({animalData}) => {
    const {data: keepers,isLoading,error} = useFetch(`http://localhost:8080/keeper`);
    const{refresh} = animalData;
    const history = useHistory();
    const [formData,setFormData] = useState({
        name:'',
        species:'',
        birthdate:'',
        temperament:'',
        enclosure:'',
        keeperID:''
    })
   
    const handleSubmit = (e) => {
        e.preventDefault();

        fetch('http://localhost:8080/animal/new',{
            method:'POST',
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(formData)
        }).then(()=>refresh())

        history.push('/');
    }

    const handleChange = (e) => {
        const {name,value} = e.target;
        setFormData({
            ...formData,
            [name]:value, 
        })
    }

    return (
        <div className="create-animal" data-testid='create-animal'>
            <h1>Register New Animal</h1>
            {error && <div>{error}</div>}
            {isLoading && <div>Loading...</div>}
            {keepers &&
            <form onSubmit={handleSubmit}>
                <label htmlFor="name">Name:</label>
                <input required  type="text" id="name" name="name" value={formData.name} onChange={handleChange} />
                <label>Species:</label>
                <input required type="text" id="species" name="species" value={formData.species}  onChange={handleChange} />
                <label>Birthdate:</label>
                <input required type="date" id="birthdate" name="birthdate"  value={formData.birthdate} onChange={handleChange} />
                <label>Temperament:</label>
                <select   id="temperament" name="temperament" value={formData.temperament} onChange={handleChange}>
                    <option value=""> Select an option</option>
                    <option value="Even">Even</option>
                    <option value="Playful">Playful</option>
                    <option value="Cautious">Cautious</option>
                    <option value="Agressive">Agressive</option>
                    <option value="Deadly">Deadly</option>
                </select>
                <label>Enclosure:</label>
                <input required type="text" id="enclosure" name="enclosure" value={formData.enclosure} onChange={handleChange} />
                <label>Keeper:</label>
                <select required id="keeperID" name="keeperID" value ={formData.keeperID} onChange={handleChange}>
                    <option value=""> Select an option</option>
                    {keepers.map(keeper=>(
                        <option value={keeper.id}>{keeper.firstName + ' ' + keeper.lastName + ' - ' + keeper.title}</option>
                    )
                    )}
                </select>
                <button>Save</button>
            </form>

            }
        </div>
    )
}
export default CreateAnimal;
