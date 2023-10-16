import { useHistory, useParams } from "react-router-dom/cjs/react-router-dom.min";
import useFetch from "../hooks/useFetch";
import FeedingList from "./FeedingList"
import { useSelector, useDispatch } from 'react-redux'
import React, { useEffect } from 'react';
import { selectAnimalByID, deleteAnimal } from "../redux/animalSlice";



const AnimalDetails = ({animalData}) => {
    const {id} = useParams();
    const history = useHistory();

    const dispatch = useDispatch();
    const animal = useSelector(state => selectAnimalByID(state,id))




    const handleDelete = () => {
        dispatch(deleteAnimal(id)).then(() => history.push('/'))
    }
    
    return (
        <div className='animal-details' data-testid='animal-details'>
            {animal && (
                <div className="container text-center">
                    <h1 data-testid='animal-details-name'>{animal.name}</h1>
                <div className="row row-cols-3">
                    <div className="col">
                        <span >Enclosure</span> 
                        <p data-testid='animal-details-enclosure'>{animal.enclosure}</p>
                    </div>
                    <div className="col">
                    <span > Temperament</span>
                        <p className={animal.temperamentWarning === 1 ?"animal-warning":""} data-testid='animal-details-temperament'>{animal.temperament}</p>
                    </div>
                    <div className="col">
                    <span >Species</span>
                        <p data-testid='animal-details-species'>{animal.species}</p>
                    </div>
                    <div className="col">
                    <span >Age</span>
                        <p data-testid='animal-details-age'>{animal.age}</p>
                    </div>
                    <div className="col">
                    <span >Keeper</span>
                        <p data-testid='animal-details-keeper'>{animal.keeperFirstName}</p>
                    </div>
                </div>
                <button onClick={handleDelete} data-testid='animal-details-delete-bt'>Delete Animal</button>
                <FeedingList id={animal.id} keeperID={animal.keeperID} animalData={animalData}/>
                </div>

            )}
        </div>
    )
 }
 export default AnimalDetails;