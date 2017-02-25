import sql


class LoginModel:
    """
    Validate user in database
    """
    @staticmethod
    def check_user_in_db_by_mail_model(mail, password):
        """
        Check if user exists in database.
        :param mail:
        :param password:
        :return: list[ID, MAIL, ACCOUNT_TYPE]
        """
        sql_obj = sql.query("SELECT ID, MAIL, ACCOUNT_TYPE FROM USERS WHERE MAIL=? AND PASSWORD=?;", [mail, password])
        mail_password_list = [sql_obj[0][0], sql_obj[0][1], sql_obj[0][2]]
        return mail_password_list

