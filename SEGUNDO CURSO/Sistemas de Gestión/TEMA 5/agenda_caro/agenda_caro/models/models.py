from odoo import models, fields

class AgendaTelefonica(models.Model):
    _name = 'agenda_caro.agenda_caro'
    _description = 'Agenda Telefónica'

    # Campos requeridos para la agenda telefónica
    nombre = fields.Char(string="Nombre", required=True)
    telefono = fields.Char(string="Teléfono", required=True)

    # Relación con el modelo 'res.partner' de Contactos
    contacto_id = fields.Many2one('res.partner', string="Contacto", help="Asociar con un contacto")
