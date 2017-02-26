import sqlite3


def connect_db(db='bacon_squad.db'):
    return sqlite3.connect(db)


def query(query, params=''):
    """
    :param query: query with ?
    :param params: list or tuple of params (replace ?)
    :return: list of object or null
    """
    # query = "SELECT * FROM Users WHERE `E-mail` =? and `password`=?"
    # params = list([login, password])

    query_result = list()
    conn = connect_db()
    conn.row_factory = sqlite3.Row
    c = conn.cursor()

    try:
        for row in c.execute(query, params):

            query_result.append(row)
    except sqlite3.OperationalError:
        print('Query ' + query + ' is wrong')

    conn.commit()

    close_db(conn)

    if query_result != []:
        return query_result


def close_db(conn):
    conn.close()
