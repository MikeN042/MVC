import { useState, useEffect } from "react"

const useFetch = (url) => {

    const [data,setData] = useState(null)
    const [isLoading,setIsLoading] = useState(true)
    const [error,setError] = useState(null)
    const [update,setUpdate] = useState(0);


    const refresh = ()=>{
        setUpdate(update + 1);
    }


    useEffect(()=>{
        console.log('Running Effect');
        fetch(url)
            .then(res =>{
                if(!res.ok){
                    throw Error('something went wrong during loading')
                }
                return res.json()
            })
            .then(data=>{
                setIsLoading(false)
                setError(null)
                setData(data)
            })
            .catch(err=>{
                setError(err.message)
                setIsLoading(false)
            })
    },[url,update])

    return{data,isLoading,error,update,refresh}
}

export default useFetch;