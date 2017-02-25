import sql


class LoginModel:
    """
    Validate user in database
    """
    @classmethod
    def check_user_in_db_by_mail_model(cls, mail, password):
        """
        Check if user exists in database.
        :param mail:
        :param password:
        :return: list
        """
        sql_obj = sql.query("SELECT ID, MAIL FROM USERS WHERE MAIL=? AND PASSWORD=?;", [mail, password])
        mail_pasword_list = [sql_obj[0][0], sql_obj[0][1]]
        return mail_pasword_list
