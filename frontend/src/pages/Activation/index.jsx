import { Alert } from "../../shared/components/Alert";
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
export function Activation() {


  const { token } = useParams()
  const { apiProgress, setApiProgres } = useState();
  const { succesMessage, setSuccessMessage } =  useState('success');
  const { errorMessage, setErrorMessage } = useState('error');

  useEffect(() => {
    async function activate() {
      setApiProgres(true);
      try {
        const response = await activate(token);
        setSuccessMessage(response.data.message);
      } catch (axiosError) {
        setErrorMessage(axiosError.response.data.message);
      } finally {
        setApiProgres(false);
      }
    }
    activate();
  }, []);
  
  return (
    <>
      {apiProgress && (
        <span className="spinner-border" aria-hidden="true"></span>
      )}
      {succesMessage && ( <Alert>{succesMessage}</Alert>
      )}
      {errorMessage && <Alert styleType="danger">{errorMessage}</Alert>}
    </>
  );
}
