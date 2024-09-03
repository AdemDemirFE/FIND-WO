// import { activateUser } from "./api";
// import { Alert } from "@/shared/components/Alert";
// import { Spinner } from "@/shared/components/Spinner";
// import { useRouteParamApiRequest } from "@/shared/hooks/useRouteParamApiRequest";
import { useParams } from "react-router-dom"
export function Activation() {


  const { token } = useParams()
  
  return <div>Activation page</div>

  // const { apiProgress, data, error } = useRouteParamApiRequest(
  //   "token",
  //   activateUser
  // );
  // return (
  //   <>
  //     {apiProgress && (
  //       <Alert styleType="secondary" center>
  //         <Spinner />
  //       </Alert>
  //     )}
  //     {data?.message && <Alert>{data.message}</Alert>}
  //     {error && <Alert styleType="danger">{error}</Alert>}
  //   </>
  // );
}
