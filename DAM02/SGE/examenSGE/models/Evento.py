
class Evento(models.Model):
    _name = "examen.evento" #Nombre de la tabla en PostgreSQL
    _description= "Descripción del modelo" 
    
    name = fields.Char(string="nombre", requiered=True)
    date = fields.Date(string="fecha")
    responsable = fields.Many2one(comodel_name="res.users")