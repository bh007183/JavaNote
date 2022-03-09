import * as actions from "../actions"
import axios from "axios"
export const api = ({dispatch}) => next => async action => {
    if(action.type !== actions.api_start.type) return next(action)
    next(action)
    const {url, method, headers, data, onSuccess, onError} = action.payload
    try{
        const response =  await axios.request({
            url,
            method,
            headers,
            data,
            onSuccess,
            onError
        })
        dispatch(actions.api_end(response.data))
        if(onSuccess)dispatch({type: onSuccess, payload: response.data})

    }catch(error){
        dispatch(actions.api_error(error.response.data))
        if(onError)dispatch({type: onError, payload: error.response.data})

    }

}