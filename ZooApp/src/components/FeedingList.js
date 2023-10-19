import useFetch from "../hooks/useFetch";
import { selectFeedingsByAnimalID, selectAllFeedings, fetchfeedings, deleteFeeding, addFeeding } from "../redux/feedingSlice";
import { useSelector, useDispatch } from 'react-redux'
import React, { useEffect } from 'react';



const FeedingList = ({animalID,keeperID}) => {
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchfeedings(animalID));
      }, [dispatch]);

    const { feedings, status, error } = useSelector(selectAllFeedings);

    const handleDeleteClick = (id) => dispatch(deleteFeeding(id))
    const handleAddFeedingClick = () => dispatch(addFeeding({animalID,keeperID}))

    return ( 
        <div className='feeding-list' data-testid='feeding-list'>
           <h3>Feedings</h3>
           <button onClick={handleAddFeedingClick} data-testid='feeding-list-add-bt'>Add Feeding</button>
           {status === 'loading' && <div>Loading...</div>} 
            {feedings && 
           feedings.map(feeding=> (
            <div className="feeding container text-center" key={feeding.id} data-testid='feeding-list-feedings'>
                <div className="row row-cols-3">
                    <div className="col">{feeding.date}</div>
                    <div className="col">{feeding.keeperFirstName}</div>
                    <div className="col"><button onClick ={()=>handleDeleteClick(feeding.id)} data-testid='feeding-list-feedings-delete-btn'>Delete</button></div>
                </div>
            </div>
           )
           )} 
        {error && <div>{error}</div>}     
        </div>    
     );


}
 
export default FeedingList;