import { useEffect, useMemo, useState } from "react";
import { signUp } from "./api";
import { Input } from "./components/Input";
import { useTranslation } from "react-i18next";
import { LanguageSelector } from "../../shared/components/LanguageSelector";

export function SignUp() {
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [passwordRepeat, setPasswordRepeat] = useState();
  const [apiProgress, setApiProgress] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const { t } = useTranslation();
  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        username: undefined,
      };
    });
  }, [username]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async () => {
    event.preventDefault();
    setSuccessMessage();
    setGeneralError();
    setApiProgress(true);

    try {
      const response = await signUp({
        username: username,
        email: email,
        password: password,
      });
      setSuccessMessage(response.successMessage);
    } catch (axiosError) {
      if (
        axiosError.response?.data 
      ) {
        if( axiosError.response.data.status === 400) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          setGeneralError(axiosError.response.data.message)
        }
      } else {
        setGeneralError("Unexpected error occurred. Please try again");
      }
    } finally {
      setApiProgress(false);
    }
  };

  const passwordRepeatError = useMemo(() => {
    if (password && password !== passwordRepeat) {
      return "Passwords do not match";
    } return '';
  }, [password, passwordRepeat]);

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1> {t('signUp')} </h1>
          </div>
          <div className="card-body">
            <Input
              id="username"
              label={t('username')}
              error={errors.username}
              onChange={(event) => setUsername(event.target.value)}
            />
            <Input
              id="email"
              label={t('email')}
              error={errors.email}
              onChange={(event) => setEmail(event.target.value)}
            />
            <Input
              id="password"
              label={t('password')}
              error={errors.password}
              onChange={(event) => setPassword(event.target.value)}
              type="password"
            />
            <Input
              id="passwordRepeat"
              label={t('passwordRepeat')}
              error={passwordRepeatError}
              type="password"
              onChange={(event) => setPasswordRepeat(event.target.value)}
            />

            {successMessage && (
              <div className="alert alert-success"> {successMessage}</div>
            )}

            {generalError && (
              <div className="alert alert-danger"> {generalError}</div>
            )}
            <div className="text-center">
              <button
                className="btn btn-primary"
                type="submit"
                disabled={
                  apiProgress || !password || password !== passwordRepeat
                }
                onClick={onSubmit}
              >
                {apiProgress && (
                  <span
                    className="spinner-border spinner-border-sm"
                    aria-hidden="true"
                  ></span>
                )}
                {t('submit')}
              </button>
            </div>
          </div>
        </form>
        <LanguageSelector />
      </div>
    </div>
  );
}
