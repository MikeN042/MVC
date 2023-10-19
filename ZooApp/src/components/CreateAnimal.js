import { useState } from "react"
import {useHistory} from 'react-router-dom';
import { addAnimal, selectAllAnimals } from "../redux/animalSlice";
import { useSelector, useDispatch } from 'react-redux'
import { selectAllKeepers } from "../redux/keeperSlice";




const CreateAnimal = () => {
    const dispatch = useDispatch();
    const history = useHistory();

    const { keepers } = useSelector(selectAllKeepers);
    const { status, error } = useSelector(selectAllAnimals);
    
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
        dispatch(addAnimal(formData)).then(() =>{
            if(status === 'succeeded'){
                history.push('/')
            }
        });
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
            {keepers &&
            <form onSubmit={handleSubmit}>
                <label htmlFor="name">Name:</label>
                <input required  type="text" id="name" name="name" value={formData.name} onChange={handleChange} data-testid='create-animal-input-name' />
                <label>Species:</label>
                <input required type="text" id="species" name="species" value={formData.species}  onChange={handleChange} data-testid='create-animal-input-species' />
                <label>Birthdate:</label>
                <input required type="date" id="birthdate" name="birthdate"  value={formData.birthdate} onChange={handleChange} data-testid='create-animal-input-birthdate' />
                <label>Temperament:</label>
                <select required id="temperament" name="temperament" value={formData.temperament} onChange={handleChange} data-testid='create-animal-input-temperament'>
                    <option value=""> Select an option</option>
                    <option value="Even">Even</option>
                    <option value="Playful">Playful</option>
                    <option value="Cautious">Cautious</option>
                    <option value="Agressive">Agressive</option>
                    <option value="Deadly">Deadly</option>
                </select>
                <label >Enclosure:</label> 
                <input required type="text" id="enclosure" name="enclosure" value={formData.enclosure} onChange={handleChange} data-testid='create-animal-input-enclosure' />
                <label>Keeper:</label>
                <select required id="keeperID" name="keeperID" value ={formData.keeperID} onChange={handleChange} data-testid='create-animal-input-keeper'> 
                    <option value=""> Select an option</option>
                    {keepers.map(keeper=>(
                        <option key={keeper.id} value={keeper.id}>{keeper.firstName + ' ' + keeper.lastName + ' - ' + keeper.title}</option>
                    )
                    )}
                </select>
                <button data-testid='create-animal-save-bt'>Save</button>
            </form>

            }
            {error && <div data-testid='create-animal-loading-error'>{error}</div>}

        </div>
    )
}
export default CreateAnimal;
