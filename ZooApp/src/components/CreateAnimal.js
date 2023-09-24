import { useState } from "react"
import useFetch from "../hooks/useFetch";



const CreateAnimal = () => {
    const [name,setName] = useState('');
    const [species,setSpecies] = useState('');
    const [birthdate,setBirthdate] = useState('');
    const [temperament,setTemperament] = useState('');
    const [enclosure,setEnclosure] = useState('');
    const [keeperID,setKeeperID] = useState('');

    const {data: keepers,isLoading,error} = useFetch(`http://localhost:8080/keeper`);

    const handleSubmit = (e) => {
        e.preventDefault();
        const animal = {name,species,birthdate,temperament,enclosure, keeperID};

        fetch('http://localhost:8080/animal/new',{
            method:'POST',
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(animal)
        }).then(() => {
            console.log('new animal added')
        })
        console.log(animal);
    }

    return (
        <div className="create-animal">
            <h1>Register New Animal</h1>
            {error && <div>{error}</div>}
            {isLoading && <div>Loading...</div>}
            {keepers &&
            <form onSubmit={handleSubmit}>
                <label>Name:</label>
                <input type="text" required onChange={(e)=>setName(e.target.value)} />
                <label>Species:</label>
                <input type="text" required onChange={(e)=>setSpecies(e.target.value)} />
                <label>Birthdate:</label>
                <input type="date" required onChange={(e)=>setBirthdate(e.target.value)} />
                <label>Temperament:</label>
                <select  required onChange={(e)=>setTemperament(e.target.value)}>
                    <option value=""> Select an option</option>
                    <option value="Even">Even</option>
                    <option value="Playful">Playful</option>
                    <option value="Cautious">Cautious</option>
                    <option value="Agressive">Agressive</option>
                    <option value="Deadly">Deadly</option>
                </select>
                <label>Enclosure:</label>
                <input type="text" required onChange={(e)=>setEnclosure(e.target.value)} />
                <label>Keeper:</label>
                <select  required onChange={(e)=>setKeeperID(e.target.value)}>
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
