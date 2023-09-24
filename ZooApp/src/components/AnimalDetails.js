import { useParams } from "react-router-dom/cjs/react-router-dom.min";
import useFetch from "../hooks/useFetch";


const AnimalDetails = () => {
    const {id} = useParams();
    const {data: animal,isLoading,error} = useFetch(`http://localhost:8080/animal/${id}`);
    
    return (
        <div className='animal-details'>
            {error &&  <div>{error}</div>}
            {isLoading && <div>Loading...</div>}
            {animal && (
                <div className="container text-center">
                    <h1>{animal.name}</h1>
                <div className="row row-cols-3">
                    <div className="col">
                        <span >Enclosure</span> 
                        <p>{animal.enclosure}</p>
                    </div>
                    <div className="col">
                    <span > Temperament</span>
                        <p style={animal.temperamentWarning === 1 ?{'color':'#f1356d',"fontWeight":"bold"}:{}}>{animal.temperament}</p>
                    </div>
                    <div className="col">
                    <span >Species</span>
                        <p>{animal.species}</p>
                    </div>
                    <div className="col">
                    <span >Age</span>
                        <p>{animal.age}</p>
                    </div>
                    <div className="col">
                    <span >Keeper</span>
                        <p>{animal.keeperFirstName}</p>
                    </div>
                </div>
                </div>

            )}
        </div>
    )
 }
 export default AnimalDetails;