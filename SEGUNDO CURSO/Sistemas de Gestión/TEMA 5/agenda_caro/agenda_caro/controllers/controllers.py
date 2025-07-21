# -*- coding: utf-8 -*-
# from odoo import http


# class AgendaCaro(http.Controller):
#     @http.route('/agenda_caro/agenda_caro', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/agenda_caro/agenda_caro/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('agenda_caro.listing', {
#             'root': '/agenda_caro/agenda_caro',
#             'objects': http.request.env['agenda_caro.agenda_caro'].search([]),
#         })

#     @http.route('/agenda_caro/agenda_caro/objects/<model("agenda_caro.agenda_caro"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('agenda_caro.object', {
#             'object': obj
#         })

