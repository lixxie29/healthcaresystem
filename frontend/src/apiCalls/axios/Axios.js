import axios from 'axios';

const client = axios.create({
    baseUrl: url,
});

const onError = function (error) {
    console.error('request failed:', error.config);
    console.error('error details:', error);

    return client(options)
        .then(onSuccess)
        .catch(onError)
        .finally(() => configureStore.dispatch(decrementAxiosLoading()));
}

export default request;